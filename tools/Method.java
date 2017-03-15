package tools;

import java.util.List;

/**
 * Created by Мария on 13.03.2017.
 */
public class Method {
    private String className;
    private List<String> fields;
    private List<String> globalFields;
    private String sourse;
    private List<String> methodsUsed;
    private String asseccSpec;
    private String name;

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

    public List<String> getGlobalFields() {
        return globalFields;
    }

    public void setGlobalFields(List<String> globalFields) {
        this.globalFields = globalFields;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public String getSourse() {
        return sourse;
    }

    public void setSourse(String sourse) {
        this.sourse = sourse;
    }

    public List<String> getMethodsUsed() {
        return methodsUsed;
    }

    public void setMethodsUsed(List<String> methodsUsed) {
        this.methodsUsed = methodsUsed;
    }
}
