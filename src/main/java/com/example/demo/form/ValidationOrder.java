package com.example.demo.form;

import com.example.demo.form.ValidationGroups.NotBlankGroup;
import com.example.demo.form.ValidationGroups.SizeCheckGroup;

import jakarta.validation.GroupSequence;

@GroupSequence({ NotBlankGroup.class, SizeCheckGroup.class })
public interface ValidationOrder {
}
