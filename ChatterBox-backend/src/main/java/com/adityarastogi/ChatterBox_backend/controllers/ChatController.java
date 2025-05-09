package com.adityarastogi.ChatterBox_backend.controllers;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import com.adityarastogi.ChatterBox_backend.entities.Message;
import com.adityarastogi.ChatterBox_backend.entities.Room;
import com.adityarastogi.ChatterBox_backend.playload.MessageRequest;
import com.adityarastogi.ChatterBox_backend.repositories.RoomRepository;
import com.adityarastogi.ChatterBox_backend.utils.EncryptionUtil;

import java.time.LocalDateTime;

@Controller
@CrossOrigin(origins = "${app.cors.allowed-origin}")
public class ChatController {


    private RoomRepository roomRepository;

    public ChatController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }


    //for sending and receiving messages
    @MessageMapping("/sendMessage/{roomId}") // /app/sendMessage/roomId
    @SendTo("/topic/room/{roomId}") // subscribe
    public Message sendMessage(
            @DestinationVariable String roomId,
            @RequestBody MessageRequest request
    ) {
        Room room = roomRepository.findByRoomId(request.getRoomId());
        Message message = new Message();
        try {
            // Encrypt the message content
            String encryptedContent = EncryptionUtil.encrypt(request.getContent());
            message.setContent(encryptedContent);
            message.setSender(request.getSender());
            message.setTimeStamp(LocalDateTime.now());

            if (room != null) {
                room.getMessages().add(message);
                roomRepository.save(room);
            } else {
                throw new RuntimeException("room not found !!");
            }

            // Decrypt the message content before sending it to the client
            message.setContent(EncryptionUtil.decrypt(encryptedContent));
        } catch (Exception e) {
            throw new RuntimeException("Error while encrypting/decrypting message", e);
        }

        return message;
    }
}