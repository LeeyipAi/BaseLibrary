package jmm.baselibrary.data.net.interceptor;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignatureInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        Request newRequest = null;
        if (request.body() instanceof FormBody) {
//            FormBody.Builder newFormBody = new FormBody.Builder();
            Map<String, String> params = new HashMap<>();
            FormBody oidFormBody = (FormBody) request.body();
            for (int i = 0; i < oidFormBody.size(); i++) {
                String key = oidFormBody.encodedName(i);
                if (key.equals("time") || key.equals("context") || key.equals("scret")) {
                    builder.addHeader(oidFormBody.name(i), oidFormBody.value(i));
                } else if (key.equals("Authorization")) {
                    builder.addHeader(oidFormBody.name(i), oidFormBody.value(i));
                    builder.addHeader("Content-Type", "application/json");
                }else{
                    params.put(oidFormBody.name(i), oidFormBody.value(i));
                }
            }
            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(params));
            builder.method(request.method(), body);
            newRequest = builder.build();
        }
        return chain.proceed(newRequest);
    }
}
