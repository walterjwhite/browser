package com.walterjwhite.browser.plugins.vanguard;

import java.util.Scanner;
import org.junit.Test;

public class ScannerReadStdin {
  @Test
  public String readStdin() {
    try (final Scanner scanner = new Scanner(System.in)) {
      return scanner.next();
    }
  }
}
