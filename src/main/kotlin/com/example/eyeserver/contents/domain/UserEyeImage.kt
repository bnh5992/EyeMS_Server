package com.example.eyeserver.contents.domain

import jakarta.persistence.Id
import org.bson.types.Binary
import org.springframework.data.mongodb.core.mapping.Document
import java.util.Date

@Document(collection = "eyeImage")
class UserEyeImage (
    @Id
    val userId : String,

    val date : Date,
    val firstImage : Binary,
    val secondImage : Binary,
    val thirdImage : Binary,
    )