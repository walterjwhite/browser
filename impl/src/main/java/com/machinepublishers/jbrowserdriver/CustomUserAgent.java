package com.machinepublishers.jbrowserdriver;

public class CustomUserAgent extends UserAgent {
  public CustomUserAgent(
      Family family,
      String vendor,
      String platform,
      String oscpu,
      String appVersion,
      String userAgentString) {
    super(family, vendor, platform, oscpu, appVersion, userAgentString);
  }

  public String getUserAgentString() {
    return userAgentString();
  }
}
