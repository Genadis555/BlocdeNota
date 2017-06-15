package mx.edu.cetis108.blocdenota;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Telmex on 14/06/2017.
 */

public class AgregarNota extends AppCompatActivity {
    String type;
    EditText TITLE,CONTENT;
    Button Add;
    private static final int SALIR= Menu.FIRST;
    AdaptadorBD DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_nota);

        Add=(Button)findViewById(R.id.button_Add);
        CONTENT=(EditText)findViewById(R.id.editText_Nota);
        TITLE=(EditText)findViewById(R.id.editText_Titulo);
        Bundle bundle=this.getIntent().getExtras();
        String content,getTitle;
        getTitle=bundle.getString("title");
        content=bundle.getString("content");
        type=bundle.getString("type");

        if (type.equals("add")){
            Add.setText("Add nota");
        }
        else {
            if (type.equals("add")){
                TITLE.setText(getTitle);
                CONTENT.setText(content);
                Add.setText("Updata nota");
            }
        }
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUpdateNotes();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        super.onCreateOptionsMenu(menu);

        menu.add(1,SALIR,0,R.string.menu_salir);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id =item.getItemId();
        switch (id){
            case SALIR:
                CookieSyncManager.createInstance(this);
                CookieManager cookieManager=CookieManager.getInstance();
                cookieManager.removeAllCookie();
                Intent intent=new Intent(AgregarNota.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void addUpdateNotes(){
        DB=new AdaptadorBD(this);
        String title,content,msj;
        title=TITLE.getText().toString();
        content=CONTENT.getText().toString();
        if (type.equals("add")){
            msj="Ingrese un titulo";
            TITLE.requestFocus();
            Mensaje(msj);
        }
    }
    public void Mensaje(String msj){
        Toast toast=Toast.makeText(this,msj,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
        toast.show();
    }
}
