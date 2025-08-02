import 'package:flutter/material.dart';
import 'package:awesome_snackbar_content/awesome_snackbar_content.dart';

class SnackBarService {
  static void snackBarFailure(BuildContext context,String message) {
    final snackBar = SnackBar(
      elevation: 0,
      behavior: SnackBarBehavior.floating,
      backgroundColor: Colors.transparent,
      content: AwesomeSnackbarContent(
        title: 'Error!',
        message: message,
        contentType: ContentType.failure,
      ),
    );

    ScaffoldMessenger.of(context).showSnackBar(snackBar);
  }

  static void snackBarSuccess(BuildContext context,String message) {
    final snackBar = SnackBar(
      elevation: 0,
      behavior: SnackBarBehavior.floating,
      backgroundColor: Colors.transparent,
      content: AwesomeSnackbarContent(
        title: 'Success!',
        message: message,
        contentType: ContentType.success,
      ),
    );

    ScaffoldMessenger.of(context).showSnackBar(snackBar);
  }

  static void snackBarWarning(BuildContext context,String message) {
    final snackBar = SnackBar(
      elevation: 0,
      behavior: SnackBarBehavior.floating,
      backgroundColor: Colors.transparent,
      content: AwesomeSnackbarContent(
        title: 'Warning!',
        message: message,
        contentType: ContentType.warning,
      ),
    );

    ScaffoldMessenger.of(context).showSnackBar(snackBar);
  }

  static void snackBarInfo(BuildContext context,String message) {
    final snackBar = SnackBar(
      elevation: 0,
      behavior: SnackBarBehavior.floating,
      backgroundColor: Colors.transparent,
      content: AwesomeSnackbarContent(
        title: 'Info!',
        message: message,
        contentType: ContentType.help,
      ),
    );

    ScaffoldMessenger.of(context).showSnackBar(snackBar);
  }

}
