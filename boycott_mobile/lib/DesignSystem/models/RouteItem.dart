import 'package:flutter/cupertino.dart';

class RouteItem{
  final String title;
  final String route;
  final IconData icon;
  final int infoCount;
  late final bool ?activeItem;
  final List<String>? permission;
  RouteItem({required this.title, required this.route, required this.icon,this.infoCount=0,this.activeItem,this.permission });
}