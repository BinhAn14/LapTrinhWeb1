package com.example.Lab05.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


import com.example.Lab05.models.User;

@Controller
public class UserController {
    List<User> listUsers = new ArrayList<>();

    public UserController() {
       User u1= new User("1","Cao Nguyên Bình An", "an100277@donga.edu.vn");
       User u2= new User("2","An Bình Cao Nguyên", "nguyen772001@donga.edu.vn");
       listUsers.add(u1);
       listUsers.add(u2);
    }
@GetMapping("/users")
@ResponseBody
    public List<User> getListUsers() {
        return listUsers;
    }

@GetMapping("/user/{id}")
@ResponseBody
        public ResponseEntity<User> getUserById(@PathVariable("id") String id){
            for(User u : listUsers){
                if(u.getId().equals(id)){
                    return ResponseEntity.status(200).body(u);

            }
        }
        return ResponseEntity.status(404).body(null);
}


   //3. API delete user by id
   @DeleteMapping("users/{id}")
   @ResponseBody
   public List<User> deleteUser(@PathVariable("id") String userId) {
       for (User user : listUsers) {
           if (user.getId().equals(userId)) {
               listUsers.remove(user);
               break;
           }
       }
       return listUsers;
   }


   //4. API POST new user
   // CREATE: Thêm một User mới (POST /users)
   @PostMapping("/user")
   @ResponseBody
   public ResponseEntity<User> createUser(@RequestBody User newuser) {
       listUsers.add(newuser);
       return ResponseEntity.status(201).body(newuser);
   }


   //5. API PUT user by id
   @PutMapping("/users/{id}")
   @ResponseBody
   public ResponseEntity<User> updateUser(@PathVariable("id") String userId, @RequestBody User updateUser) {
       for (User user : listUsers) {
           if (user.getId().equals(userId)) {
               user.setName(updateUser.getName());
               user.setEmail(updateUser.getEmail());


               return ResponseEntity.status(200).body(user);// Trả về status 200 OK và user đã được cập nhật
           }
       }


       return ResponseEntity.status(404).body(null);
   }
}




    
    
    

