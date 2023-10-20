package com.example.sharedpreferenceslesson;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private static final String PREF_NAME = "StudentPrefs";
    private static final String KEY_STUDENT = "student";
    private static final String KEY_STUDENT_LIST = "student_list";


    public static void saveStudent(Context context, Student student) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String studentJson = gson.toJson(student);

        editor.putString(KEY_STUDENT, studentJson);
        editor.apply();
    }

    public static Student getStudent(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String studentJson = sharedPreferences.getString(KEY_STUDENT, null);

        if (studentJson != null) {
            Gson gson = new Gson();
            return gson.fromJson(studentJson, Student.class);
        } else {
            return null; // No student data found in SharedPreferences
        }
    }


    public static void saveStudents(Context context, List<Student> students) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        List<Student> pastStudents = getStudents(context);
        pastStudents.addAll(students);

        Gson gson = new Gson();
        String studentListJson = gson.toJson(pastStudents);


        editor.putString(KEY_STUDENT_LIST, studentListJson);
        editor.apply();
    }
    public static List<Student> getStudents(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String studentListJson = sharedPreferences.getString(KEY_STUDENT_LIST, null);

        if (studentListJson != null) {
            Gson gson = new Gson();
            Type studentListType = new TypeToken<List<Student>>() {}.getType();
            return gson.fromJson(studentListJson, studentListType);
        } else {
            return new ArrayList<>(); // Return an empty list if no data found
        }
    }
}

