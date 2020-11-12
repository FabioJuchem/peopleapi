package com.fabiojuchem.peopleapi.presentation.resource;

public class BasePersonTestIT {

    static final String BODY = "{\"name\":\"Fake Name\",\"document\": \" \" ,\"birthDate\": \" \"}";

    static final String BODY_WITH_CONTACT =  "{\n" +
            "    \"name\": \"Fake Name\",\n" +
            "    \"document\": \"60745888054\",\n" +
            "    \"birthDate\": \"2020-10-10\",\n" +
            "    \"contacts\": [\n" +
            "            { \n" +
            "                \"name\": \"contactName\", \n" +
            "                \"phoneNumber\": \"15156151\",\n" +
            "                \"email\": \"email@email.com\" \n" +
            "            }\n" +
            "        ]\n" +
            "}";

    static final String BODY_WITH_CONTACT_EMAIL_INVALID =  "{\n" +
            "    \"name\": \"Fake Name\",\n" +
            "    \"document\": \"60745888054\",\n" +
            "    \"birthDate\": \"2020-10-10\",\n" +
            "    \"contacts\": [\n" +
            "            { \n" +
            "                \"name\": \"contactName\", \n" +
            "                \"phoneNumber\": \"15156151\",\n" +
            "                \"email\": \"email\" \n" +
            "            }\n" +
            "        ]\n" +
            "}";

    static final String CONTACT_BODY = "{\n" +
            "    \"name\": \"contactName\", \n" +
            "    \"phoneNumber\": \"15156151\",\n" +
            "    \"email\": \"email@email.com\" \n" +
            "}";

    static final String CONTACT_BODY_INVALID_EMAIL = "{\n" +
            "    \"name\": \"contactName\", \n" +
            "    \"phoneNumber\": \"15156151\",\n" +
            "    \"email\": \"email\" \n" +
            "}";
}
