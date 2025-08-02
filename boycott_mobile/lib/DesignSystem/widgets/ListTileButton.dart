import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import '../template/border_radius.dart';

Widget ListTileButton(
    {required BuildContext context,
    Widget? trailing,
    Widget? leading,
    Widget? title,
    double? radius,
    VoidCallback? onPressed}) {
  return Column(
    children: [
      ElevatedButton(
        onPressed: onPressed,
        child: Container(
          decoration: BoxDecoration(
            borderRadius: borderRadiusAll(radius ?? 0),
          ),
          child: ListTile(
            title: title,
            leading: leading,
            trailing: trailing,
          ),
        ),
      ),
    ],
  );
}
