package com.walterjwhite.browser.plugins.vanguard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.junit.Test;

public class BufferedReaderReadStdin {
  @Test
  public String readStdin() throws IOException {
    try (final BufferedReader bufferedReader =
        new BufferedReader(new InputStreamReader(System.in))) {
      return bufferedReader.readLine();
    }
  }
}
