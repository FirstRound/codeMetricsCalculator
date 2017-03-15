package tools;

import java.util.List;

public class Attribute {
  private String name;
  private String accessSpec;

  public Attribute(String name, String accessSpec) {
    this.name = name;
    this.accessSpec = accessSpec;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAcessSpec() {
    return this.accessSpec;
  }

  public void setAcessSpec(String accessSpec) {
    this.accessSpec = accessSpec;
  }

}
