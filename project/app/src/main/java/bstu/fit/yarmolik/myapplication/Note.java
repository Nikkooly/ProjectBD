package bstu.fit.yarmolik.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Note extends AppCompatActivity {
    private static final int REQUEST_CODE_READ_CONTACTS=1;
    private static boolean READ_CONTACTS_GRANTED =false;
    ArrayList<String> contacts = new ArrayList<String>();
    EditText edit,title;
    Spinner spinner;
    private List<Phone> phones;
    ListView listView;
    private ArrayAdapter<Phone> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        init();
        listView.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);
        listView.setStackFromBottom(true);
        int hasReadContactPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
        if(hasReadContactPermission == PackageManager.PERMISSION_GRANTED){
            READ_CONTACTS_GRANTED = true;
        }
        else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CODE_READ_CONTACTS);
        }
        if (READ_CONTACTS_GRANTED){loadContacts();}
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){

        switch (requestCode){
            case REQUEST_CODE_READ_CONTACTS:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    READ_CONTACTS_GRANTED = true;
                }
        }
        if(READ_CONTACTS_GRANTED){
            loadContacts();
        }
        else{
            Toast.makeText(this, "Требуется установить разрешения", Toast.LENGTH_LONG).show();
        }
    }
    private void loadContacts() {
        String phoneNumber = null;
        Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
        String _ID = ContactsContract.Contacts._ID;
        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
        String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;
        Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
        String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;
        StringBuffer output = new StringBuffer();
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(CONTENT_URI, null, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String contact_id = cursor.getString(cursor.getColumnIndex(_ID));
                String name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));
                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(HAS_PHONE_NUMBER)));
                if (hasPhoneNumber > 0) {
                    Cursor phoneCursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            Phone_CONTACT_ID + " = ?", new String[]{contact_id}, null);
                    while (phoneCursor.moveToNext()) {
                        phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));
                    }
                    contacts.add(phoneNumber);
                }
            }
        }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, contacts);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);
    }
    public void init(){
        edit=findViewById(R.id.editText9);
        spinner=findViewById(R.id.contacts);
        title=findViewById(R.id.editText10);
        listView = (ListView) findViewById(R.id.list);
    }
    public void AddNote(View view){
        phones = new ArrayList<>();
        String tit=title.getText().toString();
        String info=edit.getText().toString();
        String ph=spinner.getSelectedItem().toString();
        Phone phone = new Phone(tit,info,ph);
        //Phone phone = new Phone("jhmjhjh","kmj","mjhjm");
        phones.add(phone);
        //adapter.notifyDataSetChanged();
        boolean result = JSONHelper.exportToJSON(this, phones);
        if(result){
            Toast.makeText(this, "Данные сохранены", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Не удалось сохранить данные", Toast.LENGTH_LONG).show();
        }
    }
}
