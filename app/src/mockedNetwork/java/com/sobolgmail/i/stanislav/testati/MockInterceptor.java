package com.sobolgmail.i.stanislav.testati;

import com.sobolgmail.i.stanislav.testati.utils.Logger;
import com.sobolgmail.i.stanislav.testati.utils.StringUtils;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Stanislav Sobol on 30.11.2017.
 * stanislav.i.sobol@gmail.com
 */

class MockInterceptor implements Interceptor {
    private static final long MOCK_SLEEP_TIME_MS = 1000;

    @Override
    public Response intercept(Chain chain) throws IOException {
        try {
            Thread.sleep(MOCK_SLEEP_TIME_MS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String jsonString = "";

        final String url = chain.request().url().toString();

        if (url.equals("http://api.ati.su/v1.0/dictionaries/currencyTypes")) {
            jsonString = loadJSONFromAsset("currency_types.json");
        }
        else if (url.equals("http://api.ati.su/v1.0/loads")) {
            jsonString = loadJSONFromAsset("cargos.json");
        }

        if (StringUtils.isEmpty(jsonString)) {
            return chain.proceed(chain.request());
        } else {
            return new Response.Builder()
                    .code(200)
                    .message(jsonString)
                    .request(chain.request())
                    .protocol(Protocol.HTTP_1_0)
                    .body(ResponseBody.create(MediaType.parse("application/json"), jsonString.getBytes()))
                    .addHeader("content-type", "application/json")
                    .build();
        }
    }

    private String loadJSONFromAsset(final String assetName) {
        String json;
        try {
            final InputStream is = MApplication.getAppContext().getAssets().open(assetName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            Logger.writeError(ex);
            return "";
        }
        return json;
    }
}
