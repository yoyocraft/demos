package org.example.algo.template;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

/**
 * @author yoyocraft
 * @date 2024/07/21
 */
public class AcmMode {

    public static void template1() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        // write code here
        out.flush();
        out.close();
        in.close();
    }

    public static void template2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            // write code here
            int a = (int) in.nval;
            in.nextToken();
        }
        out.flush();
        out.close();
        br.close();
    }
}
