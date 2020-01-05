package com.example.translate;

public class MyResult {
    String original;
    String Result;

    public String getOriginal() {
        return original;
    }

    public String getResult() {
        return Result;
    }

    public MyResult(String original, String result) {
        this.original = original;
        Result = result;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public void setResult(String result) {
        Result = result;
    }
}
