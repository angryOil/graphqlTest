package com.stargazer.graphqltest.controller

import com.stargazer.graphqltest.entity.UserEntity
import com.stargazer.graphqltest.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(private val userService: UserService) {

    @GetMapping
    fun hello():String{
        return "hello world"
    }

    @GetMapping("/join")
    fun joinUserByName(@RequestParam username: String): Long {
        return userService.joinUser(username)
    }


    @GetMapping("/test")
    fun testDsl():List<UserEntity>{
        return userService.getFirst()
    }
}