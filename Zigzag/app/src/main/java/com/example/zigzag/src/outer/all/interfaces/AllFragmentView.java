package com.example.zigzag.src.outer.all.interfaces;

import com.example.zigzag.src.outer.all.models.OuterAllResponse;

import java.util.ArrayList;

public interface AllFragmentView {

    void validateSuccess(String text);

    void validateFailure(String message);

    void getItemSuccess(boolean isSuccess, int code, String message, ArrayList<OuterAllResponse.OuterAllResult> outerAllResult);
}
