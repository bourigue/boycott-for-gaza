 import 'package:flutter/material.dart';

class  HelpFunction {
static  Future<DateTime?> getDatePicker(BuildContext context,) async {
     DateTime? pickedDate = await showDatePicker(
         context: context,
         initialDate: DateTime.now(),
         firstDate: DateTime(2000),
         lastDate: DateTime(9000));

     if (pickedDate != null) {
       return pickedDate;
     } else {
       return null;
     }
   }
}