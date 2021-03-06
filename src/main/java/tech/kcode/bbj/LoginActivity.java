package tech.kcode.bbj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.UnsupportedEncodingException;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button button = (Button) findViewById(R.id.loginButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText user = (EditText) findViewById(R.id.usernameText);
                TextView text1 = (TextView) findViewById(R.id.userTest);
                EditText pass = (EditText) findViewById(R.id.passwordText);
                TextView text2 = (TextView) findViewById(R.id.passTest);
                text1.setText(user.getText().toString());
                text2.setText(pass.getText().toString());
                JSONObject jsonBody = new JSONObject();
                try {
                    jsonBody.put("", "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                postRequest("thread_index", jsonBody);

            }
        });


    }

    public void postRequest(String endpoint, JSONObject MyData) {
        //final TextView text = (TextView)findViewById(R.id.resultText);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL = "http://45.63.79.85/api/" + endpoint;
        JSONObject jsonBody = MyData;
        final String mRequestBody = jsonBody.toString();
        //text.setText(mRequestBody);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("LOG_VOLLEY", error.toString());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");

                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                    return null;
                }
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {

                    switch (responseString = String.valueOf(response.statusCode)) {
                    }

                }
                try {
                    String r = new String(response.data, "UTF-8");
                    MainActivity.result = r;
                    //text.setText(MainActivity.result);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
        };

        requestQueue.add(stringRequest);

    }


}
