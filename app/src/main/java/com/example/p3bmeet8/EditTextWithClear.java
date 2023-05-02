package com.example.p3bmeet8;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.res.ResourcesCompat;

public class EditTextWithClear extends AppCompatEditText {

    Drawable mClearButton;

    private void init () {
    mClearButton = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_clear_copaque_24dp, null);

    addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            showClearButton();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    });

    setOnTouchListener(new OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            // Kalo ada clear button di urutan di end maka isi 2
            if(getCompoundDrawablesRelative()[2] != null) {
                float clearButtonStartPosition = (getWidth() - getPaddingEnd() - mClearButton.getIntrinsicWidth());

                boolean isButtonClicked = false;

                if (motionEvent.getX() > clearButtonStartPosition) {
                    isButtonClicked = true;
                }

                if (isButtonClicked) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        mClearButton = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_clear_black_24dp, null);
                        showClearButton();
                    }

                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        mClearButton = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_clear_copaque_24dp, null);
                        getText().clear();
                        hideClearButton();
                        return true;
                    }
                }

                else {
                    return false;
                }
            }
            return false;
        }
    });

    }

    public EditTextWithClear(@NonNull Context context) {
        super(context);
        init();
    }

    public EditTextWithClear(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditTextWithClear(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void showClearButton() {
        // Mau menambhakan componen drawable ke componen edit text... Karena kita ingin meletakkan di kanan, maka yang diisi bagian end-nya
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, mClearButton, null);
    }

    private void hideClearButton() {
        // Karena mau di hide maka di null-kan semuanya
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null);
    }
}
