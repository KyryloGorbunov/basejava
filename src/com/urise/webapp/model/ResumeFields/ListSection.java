package com.urise.webapp.model.ResumeFields;

import java.util.ArrayList;
import java.util.List;

public class ListSection extends AbstractSection {

   public List<String> strings = new ArrayList<>();

   @Override
   public String toString() {
      return strings.toString();
   }
}
