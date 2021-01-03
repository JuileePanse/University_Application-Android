package com.npu_app.npu_application.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.npu_app.npu_application.R;
import com.npu_app.npu_application.controller.DatabaseAccess;
import com.npu_app.npu_application.model.StudentProfile;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

public class FragmentStudentProfile extends Fragment {
    private DatabaseAccess db;
    private TextView student_program, emergency_contact_name, emergency_contact, emergency_email;
    private TextView gender, citizenship, visa_status, student_address, student_email, student_contact;
    private TextView student_name;
    public String editType;
    private StudentProfile studentProfile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_student_profile, parent, false);
        db = DatabaseAccess.getInstance(getActivity());
        studentProfile = db.getStudentInfo();
        initViews(view);
        bindDataToLayout();

        view.findViewById(R.id.edit_contact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                editType = "Contact";
                setUpEditDialog(view);
            }
        });

        view.findViewById(R.id.edit_emergency_contact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                editType  = "Emergency Contact";
                setUpEditDialog(view);
            }
        });

        return view;

    }

    private void bindDataToLayout() {

        student_name.setText(studentProfile.getFirst_name() + " " +studentProfile.getLast_name());
        student_program.setText(studentProfile.getProgram());
        emergency_contact_name.setText(studentProfile.getEmergency_contact_name());
        emergency_contact.setText(studentProfile.getEmergency_contact_number());
        emergency_email.setText(studentProfile.getEmergency_contact_email());
        gender.setText("Gender: " + studentProfile.getGender());
        citizenship.setText("Citizenship: " + studentProfile.getCitizenship());
        //martial_status.setText("Martial Status: " + studentProfile.getMartial_status());
        student_address.setText(studentProfile.getStudent_address());
        student_email.setText(studentProfile.getStudent_email());
        student_contact.setText(studentProfile.getStudent_contact());
        visa_status.setText("Visa status: "+studentProfile.getVisa_status());
    }

    private void setUpEditDialog(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        ViewGroup viewGroup = view.findViewById(android.R.id.content);
        final View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_edit_contact, viewGroup, false);
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        TextView tvTitle;
        EditText etStudentContact, etStudentEmail, etStudentAddress;

        tvTitle = dialogView.findViewById(R.id.tv_title);
        etStudentContact = dialogView.findViewById(R.id.student_contact);
        etStudentAddress = dialogView.findViewById(R.id.student_address);
        etStudentEmail = dialogView.findViewById(R.id.student_email);

        tvTitle.setText(editType);
        if (editType.equals("Contact")) {
            etStudentContact.setText(studentProfile.getStudent_contact());
            etStudentAddress.setText(studentProfile.getStudent_address());
            etStudentEmail.setText(studentProfile.getStudent_email());
        }else{
            etStudentContact.setText(studentProfile.getEmergency_contact_number());
            etStudentAddress.setText(studentProfile.getEmergency_contact_name());
            etStudentEmail.setText(studentProfile.getEmergency_contact_email());
        }

        dialogView.findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }

    private void initViews(View view) {
        student_name = view.findViewById(R.id.student_name);
        student_program = view.findViewById(R.id.student_program);
        emergency_contact_name = view.findViewById(R.id.emergency_contact_name);
        emergency_contact = view.findViewById(R.id.emergency_contact);
        emergency_email = view.findViewById(R.id.emergency_email);
        gender = view.findViewById(R.id.gender);
        citizenship = view.findViewById(R.id.citizenship);
        visa_status = view.findViewById(R.id.visa_status);
        //martial_status = view.findViewById(R.id.martial_status);
        student_address = view.findViewById(R.id.student_address);
        student_email = view.findViewById(R.id.student_email);
        student_contact = view.findViewById(R.id.student_contact);
    }
}
