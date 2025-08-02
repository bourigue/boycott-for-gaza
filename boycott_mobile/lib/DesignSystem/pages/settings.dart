import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:get/get.dart';
import '../services/LangueService/bloc/lang_bloc.dart';
import '../template/spaceVH.dart';
import '../widgets/appBar.dart';
import '../widgets/Divider.dart';
import '../widgets/containerPage.dart';
import '../widgets/itemSetting.dart';
import 'languageChange/LanguageChangeDialog.dart';

class Setting extends StatefulWidget {
  const Setting({Key? key}) : super(key: key);

  @override
  State<Setting> createState() => _SettingState();
}

class _SettingState extends State<Setting> {
  openDialogChangeLanguage(BuildContext context) {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return LanguageChangeDialog();
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    final languageBloc = context.read<LanguageBloc>();

    return dsContainerPage(
        context: context,
        title: "settings".tr,
        headerChild: SizedBox(
          width: 200,
          height: 200,
          child: SvgPicture.asset(
            "assets/images/login.svg",
          ),
        ),
        child: Column(children: [
          dsDivider(),
          ItemSetting(title: 'editProfile'.tr, icon: Icons.person, route: ""),
          verticalSpaceSmall,
          ItemSetting(
              title: 'editInformations'.tr, icon: Icons.info, route: ""),
          verticalSpaceSmall,
          ItemSetting(
              title: 'changeLanguage'.tr,
              icon: Icons.language,
              route: "/LanguageChangeDialog"),
          verticalSpaceSmall,
          ItemSetting(
              title: 'changeTheme'.tr,
              icon: Icons.language,
              route: "/themeChangeDialog"),
          verticalSpaceSmall,
          ItemSetting(title: 'logout'.tr, icon: Icons.logout, route: "")
        ]));
  }
}
