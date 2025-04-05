package com.adityarastogi.ChatterBox_backend.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.adityarastogi.ChatterBox_backend.entities.Room;

public interface RoomRepository extends MongoRepository<Room, String> {
    //get room using room id
    Room findByRoomId(String roomId);
}