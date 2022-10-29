package com.example.RestApiController;

import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController //Annotation--> with this annotation I notify java In this class I will write the API's
public class MyControllers {

    // Using Hashmap to store user data
    HashMap<Integer,User> users = new HashMap<>();

    @GetMapping("/get_users")

    public List<User> getAllUser(){
        List<User> listOfUsers = new ArrayList<>();

        //Another way to add all users to my list
//        for(User user: users.values()){
//            listOfUsers.add(user);
//        }


        for(Map.Entry<Integer, User> entry :users.entrySet()){
            listOfUsers.add(entry.getValue());

        }
        return listOfUsers;
    }

    @GetMapping("/get_user")
    public User getUser(@RequestParam("id")int id){
        return users.get(id);
    }
    //URL that will be used
    @PostMapping("/add_user")
    public void addUser(@RequestParam("id")int id,@RequestParam("name") String name,
                        @RequestParam("country")String country,@RequestParam("age")int age){
        User user = new User(id,name,country,age);
        users.put(id,user);
    }

    @PostMapping("add_user_body")
    public void addUser(@RequestBody(required = true)User user){
        users.put(user.getId(), user);
    }

    @PutMapping("modify_user_body")
    public User modifyUser(@RequestBody() User user){
        users.put(user.getId(),user);
        return user;
    }

    @DeleteMapping("/delete_user")
    public void deleteUser(@RequestBody()User user){
        users.remove(user.getId(), user);

    }
        //Using path variable to delete entry
        //localhost:8080/delete_user_pathvariable/1
    @DeleteMapping("/delete_user_pathvariable/{id}")
    public void deleteUser(@PathVariable ("id") int id){
        users.remove(id);
    }


}
