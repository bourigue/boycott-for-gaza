

class Validator {

  // Validate if a value is empty
  static String? required(String? value, String errorMessage) {
    if (value == null || value.isEmpty) {
      return errorMessage;
    }
    return null;
  }

  // Validate if a value is a valid email format
  static String? email(String? value, String errorMessage) {
    final emailRegex = RegExp(r'^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$');
    if (value == null || !emailRegex.hasMatch(value)) {
      return errorMessage;
    }
    return null;
  }

  // Validate if a value has a minimum length
  static String? minLength(String? value, int minLength, String errorMessage) {
    if (value == null || value.length < minLength) {
      return errorMessage;
    }
    return null;
  }

  // Validate if a value has a maximum length
  static String? maxLength(String? value, int maxLength, String errorMessage) {
    if (value != null && value.length > maxLength) {
      return errorMessage;
    }
    return null;
  }


  // Validate if a value matches a specified pattern using RegExp
  static String? pattern(String? value, String pattern, String errorMessage) {
    final regex = RegExp(pattern);
    if (value == null || !regex.hasMatch(value)) {
       return errorMessage;
    }
    return null;
  }
 /*static String? getValidate(Field field, String value) {
    for (var validator in field.validators) {
      switch (validator) {
        case "required":
          {
            final error = Validator.required(value, field.errorMessage);
            if (error != null) {
              return error;
            }
          }
          break;
        case "email":
          {
            final error = Validator.email(value, field.errorMessage);
            if (error != null) {
              return error;
            }
          }
          break;
        case "password":
          {
            final error = Validator.minLength(value, 8, field.errorMessage);
            if (error != null) {
              return error;
            }
          }
          break;
      }
    }
    return null;
  }
*/

}
