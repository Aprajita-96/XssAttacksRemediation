package com.example.musicApp.controller;

import com.example.musicApp.model.User;
import com.example.musicApp.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apiv1")
public class MusicController {
    @Autowired
//            public MusicController(MusicService)
   private MusicService music;
    @PostMapping("/SaveMusicInfo")
    public ResponseEntity<?> save(@RequestBody @Valid User user ){
        User savedUser=music.add(user);
        return new ResponseEntity<User>(savedUser, HttpStatus.OK);
    }

    @GetMapping("/getTrack/{id}")
    public ResponseEntity<User> show(@PathVariable("id") int id){
      User displayUser =  music.get(id);
       return new ResponseEntity<User>(displayUser,HttpStatus.OK);


}
@GetMapping("/getAllId")
public ResponseEntity<?> showAll(){

        List<User> displayAllUser = music.getAll();
        return new ResponseEntity<List<User>>(displayAllUser,HttpStatus.OK);
}
    @PutMapping("/updatetrack")
    public ResponseEntity<?> update(@RequestBody User user ){
        User savedUser=music.update(user);
        return new ResponseEntity<User>(savedUser, HttpStatus.OK);
    }

@DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable ("id") int id){
      music.deleteId(id);
       return new ResponseEntity<User>( HttpStatus.OK);
}
}

