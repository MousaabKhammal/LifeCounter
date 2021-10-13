 package com.example.lifecounter.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.lifecounter.R;
import com.google.android.material.snackbar.Snackbar;

 public class  MainFragment extends Fragment {

    private MainViewModel mViewModel;

    private ImageButton livetwotoone;
    private ImageButton liveonetotwo;
    private Button p1poissonmore;
    private Button p1poissonless;
    private Button p2poissonmore;
    private Button p2poissonless;
    private ImageButton p1lifemore;
    private ImageButton p1lifeless;
    private ImageButton p2lifemore;
    private ImageButton p2lifeless;
    private TextView counter1;
    private TextView counter2;

    private int life1 = 20;
    private int life2 = 20;
    private int poisson1 = 0;
    private int poisson2 = 0;
    private View view;


     public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.main_fragment, container, false);

        liveonetotwo = view.findViewById(R.id.liveonetotwo);
        livetwotoone = view.findViewById(R.id.livetwotoone);
        p1poissonmore = view.findViewById(R.id.p1poissonmore);
        p1poissonless = view.findViewById(R.id.p1poissonless);
        p2poissonmore = view.findViewById(R.id.p2poissonmore);
        p2poissonless = view.findViewById(R.id.p2poissonless);
        p1lifemore = view.findViewById(R.id.p1lifemore);
        p1lifeless = view.findViewById(R.id.p1lifeless);
        p2poissonmore = view.findViewById(R.id.p2poissonmore);
        p2lifeless = view.findViewById(R.id.p2lifeless);
        p2poissonmore = view.findViewById(R.id.p2poissonmore);
        counter1 = view.findViewById(R.id.textView);
        counter2 = view.findViewById(R.id.textView2);
        p2lifemore = view.findViewById(R.id.p2lifemore);

        if(savedInstanceState != null){
            //Restaurar el valor de los contadores del estado guardado
            life1 = savedInstanceState.getInt("life1");
            life2 = savedInstanceState.getInt("life2");
            poisson1 = savedInstanceState.getInt("poisson1");
            poisson2 = savedInstanceState.getInt("poisson2");
            updateViews();
        }


        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.liveonetotwo:
                        life1--;
                        life2++;
                        break;
                    case R.id.livetwotoone:
                        life1++;
                        life2--;
                        break;
                    case R.id.p1poissonmore:
                        poisson1++;
                        break;
                    case R.id.p1poissonless:
                        poisson1--;
                        break;
                    case R.id.p2poissonmore:
                        poisson2++;
                        break;
                    case R.id.p2poissonless:
                        poisson2--;
                        break;
                    case R.id.p1lifeless:
                        life1--;
                        break;
                    case R.id.p1lifemore:
                        life1++;
                        break;
                    case R.id.p2lifeless:
                        life2--;
                        break;
                    case R.id.p2lifemore:
                        life2++;
                        break;
                }
                updateViews();
            }
        };

        liveonetotwo.setOnClickListener(listener);
        livetwotoone.setOnClickListener(listener);
        p1poissonmore.setOnClickListener(listener);
        p1poissonless.setOnClickListener(listener);
        p2poissonmore.setOnClickListener(listener);
        p2poissonless.setOnClickListener(listener);
        p1lifemore.setOnClickListener(listener);
        p1lifeless.setOnClickListener(listener);
        p2lifemore.setOnClickListener(listener);
        p2lifeless.setOnClickListener(listener);
        counter1.setOnClickListener(listener);
        counter2.setOnClickListener(listener);
        
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatment
        if(id ==R.id.reset) {
            reset();
            Snackbar.make(view, "New Game", Snackbar.LENGTH_LONG).show();
        }
        else if(id == R.id.out){
            showDialog();
        }

        return super.onOptionsItemSelected(item);
    }

     private void showDialog() {
         AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
         builder.setMessage("Quieres acabar el juego?")
                 .setCancelable(false)
                 .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         getActivity().finish();
                     }
                 })
                 .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {

                     }
                 })
                 .show();
     }

     @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    private void reset() {
        poisson1 = 0;
        poisson2 = 0;
        life1 = 20;
        life2 = 20;

        updateViews();

    }

    private void updateViews() {
        counter1.setText(String.format("%d/%d", life1, poisson1));
        counter2.setText(String.format("%d/%d", life2, poisson2));
    }

     /**El sistema llama a este metodo antes de que se destruya la actividad y guarda los valores
      */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        //llamar a la superclase para guardar el estado de la jerarquia de la vista
        super.onSaveInstanceState(outState);
        // guardamos los valores en el `Bundle`
        outState.putInt("life1", life1);
        outState.putInt("life2", life2);
        outState.putInt("poisson1", poisson1);
        outState.putInt("poisson2", poisson2);
    }

     @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }
 }