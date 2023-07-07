package com.test.swagger.dataCreationFromModel;

import com.github.javafaker.Faker;
import com.test.swagger.pojo.Category;
import com.test.swagger.pojo.PetPojo;
import com.test.swagger.pojo.Tag;
import com.test.swagger.pojo.UserPojo;

import java.util.ArrayList;
import java.util.HashMap;

public class PetDataCreator
{
    public static Faker fake = new Faker();
    public static PetPojo petPojo;
    public PetPojo petDataCreator(String status){
        petPojo=new PetPojo();
        petPojo.setId(fake.number().randomDigitNotZero());
        petPojo.setName(fake.name().username());
        petPojo.setStatus(status);
        Category category=new Category();
        category.setId(fake.number().randomDigitNotZero());
        category.setName(fake.name().username());
        petPojo.setCategory(category);
        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("https//test.com");
        petPojo.setPhotoUrls(arrayList);
        Tag tag=new Tag();
        tag.setId(fake.number().randomDigitNotZero());
        tag.setName(fake.funnyName().name());
        ArrayList<Tag> tagList=new ArrayList<>();
        tagList.add(tag);
        petPojo.setTags(tagList);
        return petPojo;
    }
}
