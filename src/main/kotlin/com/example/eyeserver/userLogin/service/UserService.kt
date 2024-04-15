package com.example.eyeserver.userLogin.service

import com.example.eyeserver.agency.repository.AgencyRepository
import com.example.eyeserver.userLogin.domain.Users
import com.example.eyeserver.userLogin.dto.SignUpUserDTO
import com.example.eyeserver.userLogin.dto.UnityUserInfoDTO
import com.example.eyeserver.userLogin.repository.UserRepository
import org.apache.coyote.Response
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val agencyRepository: AgencyRepository,

) {

    fun addUser(signUpUserDTO: SignUpUserDTO, agencyName : String) : Boolean{
        if(userRepository.existsByUserId(signUpUserDTO.userId)){
            return false
        }
        val agency = agencyRepository.findByAgencyName(agencyName)
        var gender : Boolean = false
        var glasses : Boolean = false
        if(signUpUserDTO.gender == "male"){
            gender = true
        }
        if(signUpUserDTO.glasses == "ok"){
            glasses = true
        }
        userRepository.save(Users(
            userId = signUpUserDTO.userId,
            password = passwordEncoder.encode(signUpUserDTO.password),
            status = null,
            name = signUpUserDTO.name,
            agencyName = agencyName,
            birth = signUpUserDTO.birth.substring(0,10),
            phone = signUpUserDTO.phone,
            email = signUpUserDTO.email,
            address = signUpUserDTO.address,
            gender = gender,
            glasses = glasses,
            agency = agency,
            visited = false
        ))
        return true
    }

    fun userInfo(id : String) : UnityUserInfoDTO {
        val user = userRepository.findByUserId(id)
        return UnityUserInfoDTO(
            userId = user.userId,
            name = user.name,
            birth = user.birth,
            phone = user.phone,
            email = user.email,
            address = user.address,
            glasses = user.glasses.toString(),
            gender = user.gender.toString(),
        )
    }

}