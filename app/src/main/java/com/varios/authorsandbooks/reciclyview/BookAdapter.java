package com.varios.authorsandbooks.reciclyview;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Library.controller.BookController;
import com.Library.entites.Book;
import com.varios.authorsandbooks.BookActivity;
import com.varios.authorsandbooks.R;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookHolder> {

    Context context;
    List<Book> listBooks;
    BookController controller;

    public BookAdapter(Context context, List<Book> listBooks, BookController controller) {
        this.context = context;
        this.listBooks = listBooks;
        this.controller = controller;
    }

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BookHolder(LayoutInflater.from(context).inflate(R.layout.book_table_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder holder, int position) {
        holder.idBook.setText(listBooks.get(position).getIdBook()+"");
        holder.title.setText(listBooks.get(position).getTitle());;
        holder.isbn.setText(listBooks.get(position).getIsbn());
        holder.yearPublication.setText(listBooks.get(position).getYearPublication()+"");
        holder.review.setText(listBooks.get(position).getReview()+"");
        holder.numSheets.setText(listBooks.get(position).getNumSheets()+"");
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAbsoluteAdapterPosition();
                controller.setBook(listBooks.get(pos));
                controller.showForm(v, context);
                
            }
        });
    }

    public void showForm(View v, String id) {
        // Inflar el layout del formulario
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_formulario, null);

        // Referencias a los campos de entrada
        EditText inputId = view.findViewById(R.id.lb_input_id);
        EditText inputTitulo = view.findViewById(R.id.lb_input_titulo);
        EditText inputIdAutor = view.findViewById(R.id.lb_input_idAutor);
        EditText inputIsbn = view.findViewById(R.id.lb_input_isbn);
        EditText inputAnioPublicacion = view.findViewById(R.id.lb_input_anioPublicacion);
        EditText inputRevision = view.findViewById(R.id.lb_input_revision);
        EditText inputNroHojas = view.findViewById(R.id.lb_input_nroHojas);


        if(!id.equals("")){
            inputIdAutor.setText(id);
        }

        // Crear el dialog con el formulario
        new AlertDialog.Builder(context)
                .setTitle("Formulario de Libro")
                .setView(view)
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Obtener los valores ingresados
                        int id = Integer.parseInt(inputId.getText().toString());
                        String titulo = inputTitulo.getText().toString();
                        int idAutor = Integer.parseInt(inputIdAutor.getText().toString());
                        String isbn = inputIsbn.getText().toString();
                        int anioPublicacion = Integer.parseInt(inputAnioPublicacion.getText().toString());
                        int revision = Integer.parseInt(inputRevision.getText().toString());
                        int nroHojas = Integer.parseInt(inputNroHojas.getText().toString());

                        // Puedes procesar los datos aquí o guardarlos en la base de datos
//                        createBook(id, titulo, idAutor, isbn, anioPublicacion, revision, nroHojas);

                        // Mostrar Toast con información guardada
                        Toast.makeText(context,
                                "Guardado: " + titulo,
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }



    @Override
    public int getItemCount() {
        return listBooks.size();
    }
}
