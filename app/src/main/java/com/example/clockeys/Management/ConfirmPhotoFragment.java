package com.example.clockeys.Management;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.clockeys.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConfirmPhotoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConfirmPhotoFragment extends Fragment {

    private ImageButton confirmButton,rejectButton;
    private ImageView imageView;
    private Bitmap bitmap;
    private onConfirmPhotoFragmentInteractionListener listener;

    public interface onConfirmPhotoFragmentInteractionListener{
        void onConfirmPressed(Bitmap bitmap);
        void onRejectPressed();
    }


    public ConfirmPhotoFragment(onConfirmPhotoFragmentInteractionListener listener){
        this.listener = listener;
    }

    public static ConfirmPhotoFragment newInstance(Bitmap bitmap, onConfirmPhotoFragmentInteractionListener listener) {
        ConfirmPhotoFragment fragment = new ConfirmPhotoFragment(listener);
        Bundle args = new Bundle();
        args.putParcelable("image",bitmap);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            bitmap = getArguments().getParcelable("image", Bitmap.class);

        }else throw new RuntimeException("No photo passed");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_confirm_photo, container, false);
        imageView = view.findViewById(R.id.fragmentPhotoTaken);
        confirmButton = view.findViewById(R.id.fragmentConfirmButton);
        rejectButton = view.findViewById(R.id.fragmentRejectButton);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onConfirmPressed(bitmap);
            }
        });

        rejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRejectPressed();
            }
        });

        imageView.setImageBitmap(bitmap);
        return view;
    }
}