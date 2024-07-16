package com.example.eventplanner;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
public class Main extends AppCompatActivity {
    private Databaseuser dbuser;
    private Databaseguest dbguest;
    private Databaseevent dbevent;
    private Button sign_up;
    private EditText email;
    private EditText password;
    private EditText ada;
    private EditText adad;
    private EditText akak;
    private Button regis;
    private Button user_login;
    private Button admin_login;
    private Button login_us;
    public String x;
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.front);
        dbuser = new Databaseuser(this);
        dbguest = new Databaseguest(this);
        dbevent = new Databaseevent(this);
    }
    public void closeApp(View view)
    {
        finish();
    }
    public void login1(View view)
    {
        setContentView(R.layout.login);
        user_login=findViewById(R.id.button2);
        admin_login=findViewById(R.id.button3);
        user_login.setOnClickListener(view1 -> {setContentView(R.layout.user_login);
        user_login1();});
        admin_login.setOnClickListener(view1 -> {setContentView(R.layout.admin_xml);
        admin_login();});

    }
    public void sign_up(View view)
    {
        setContentView(R.layout.signup);
        email=findViewById(R.id.editTextTextEmailAddress);
        password=findViewById((R.id.editTextTextPassword));
        regis=findViewById(R.id.button);
        regis.setOnClickListener(view1 ->{String emailText = email.getText().toString();
            String passwordText = password.getText().toString();
            dbuser.addUser(emailText, passwordText);});
    }
    public void back1(View view)
    {
        setContentView(R.layout.front);
    }
    public void back2(View view) {setContentView(R.layout.login_menu);}
    @SuppressLint("ResourceType")
    public void user_login1()
    {
        login_us=findViewById(R.id.Button6);
        email=findViewById(R.id.editTextTextEmailAddress1);
        password=findViewById(R.id.editTextTextPassword1);
        login_us.setOnClickListener(view -> {
            String name = dbuser.checkUser(email.getText().toString(),password.getText().toString());
            System.out.println(name);
            System.out.println(email.getText().toString());
            if(name!=null && name.equals(email.getText().toString()))
            {
                setContentView(R.layout.login_menu);
                x=name;
            }
        });
    }
    public void editcreds(View view)
    {
        setContentView(R.layout.new_login_creds);
        email=findViewById(R.id.editTextTextEmailAddress2);
        password=findViewById(R.id.editTextTextPassword2);
        login_us=findViewById(R.id.savenew);
        login_us.setOnClickListener(view1 -> {dbuser.updatecreds(email.getText().toString(),password.getText().toString(),x);});
    }
    public void addgu(View view)
    {
        setContentView(R.layout.add_guest);
        email=findViewById(R.id.editTextTextEmailAddress3);
        password=findViewById(R.id.editTextTextPhone);
        login_us=findViewById(R.id.butt);
        login_us.setOnClickListener(view1 -> {dbguest.addGuest(email.getText().toString(),Long.parseLong(password.getText().toString()),x);});
    }
    public void viewgu(View view) {
        setContentView(R.layout.manage_guest);
        LinearLayout guestContainer = findViewById(R.id.linearl);
        List<String> guestList = dbguest.showguestlist(x);
        email = findViewById(R.id.textView3);
        sign_up = findViewById(R.id.button6);

        guestContainer.removeAllViews();

        for (String guest : guestList) {
            TextView guestTextView = new TextView(this);
            guestTextView.setText(guest);
            guestContainer.addView(guestTextView);
        }

        sign_up.setOnClickListener(view1 -> {
            dbguest.deleteguest(email.getText().toString());
            guestContainer.removeAllViews();
            List<String> updatedGuestList = dbguest.showguestlist(x);
            for (String guest : updatedGuestList) {
                TextView guestTextView = new TextView(this);
                guestTextView.setText(guest);
                guestContainer.addView(guestTextView);
            }
        });
    }
    public void createve(View view)
    {
        setContentView(R.layout.create_event);
        email=findViewById(R.id.textView4);
        password=findViewById(R.id.textView5);
        ada=findViewById(R.id.textView6);
        adad=findViewById(R.id.textView7);
        akak=findViewById(R.id.textView8);
        regis=findViewById(R.id.button7);
        regis.setOnClickListener(view1 -> {dbevent.addEvent(email.getText().toString(),password.getText().toString(),ada.getText().toString(),Integer.parseInt(adad.getText().toString()),x,akak.getText().toString());});
    }
    public void vieweve(View view) {
        setContentView(R.layout.manage_event);
        LinearLayout guestContainer = findViewById(R.id.linearl3);
        List<String> guestList = dbevent.showeventlist(x);
        email = findViewById(R.id.textView32);
        sign_up = findViewById(R.id.button61);

        guestContainer.removeAllViews();
        for (String guest : guestList) {
            TextView guestTextView = new TextView(this);
            guestTextView.setText(guest);
            guestContainer.addView(guestTextView);
        }

        sign_up.setOnClickListener(view1 -> {
            dbevent.deleteEVENT(email.getText().toString());
            guestContainer.removeAllViews();
            List<String> updatedGuestList = dbevent.showeventlist(x);
            for (String guest : updatedGuestList) {
                TextView guestTextView = new TextView(this);
                guestTextView.setText(guest);
                guestContainer.addView(guestTextView);
            }
        });
    }
    public void admin_login()
    {
        login_us=findViewById(R.id.Button62);
        email=findViewById(R.id.editTextTextEmailAddress4);
        password=findViewById(R.id.editTextTextPassword4);
        login_us.setOnClickListener(view -> {
            String name = email.getText().toString();
            if(name!=null && name.equals("aadarsh") && password.getText().toString().equals("mishra"))
            {
                setContentView(R.layout.admin_loginmenu);
            }
        });
    }
    public void viewus(View view) {
        setContentView(R.layout.manage_user);
        LinearLayout guestContainer = findViewById(R.id.linearl33);
        List<String> guestList = dbuser.showuserlist();
        email = findViewById(R.id.textView323);
        sign_up = findViewById(R.id.button613);
        guestContainer.removeAllViews();
        for (String guest : guestList) {
            TextView guestTextView = new TextView(this);
            guestTextView.setText(guest);
            guestContainer.addView(guestTextView);
        }

        sign_up.setOnClickListener(view1 -> {
            dbuser.deleteuser(email.getText().toString());
            guestContainer.removeAllViews();
            List<String> updatedGuestList = dbuser.showuserlist();
            for (String guest : updatedGuestList) {
                TextView guestTextView = new TextView(this);
                guestTextView.setText(guest);
                guestContainer.addView(guestTextView);
            }
        });
    }
    public void back3(View view)
    {
        setContentView(R.layout.admin_loginmenu);
    }
    public void terminationdata(View view)
    {
        setContentView(R.layout.rest);
        sign_up=findViewById(R.id.button10);
        login_us=findViewById(R.id.button11);
        admin_login=findViewById(R.id.button12);
        sign_up.setOnClickListener(view1 -> {dbuser.eraseuser();});
        login_us.setOnClickListener(view1 -> {dbevent.eraseevent();});
        admin_login.setOnClickListener(view1 -> {dbguest.eraseguest();});
    }
}