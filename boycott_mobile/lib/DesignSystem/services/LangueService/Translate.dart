import 'package:get/get.dart';

class  MyLocal implements Translations{
  @override
  Map<String, Map<String, String>> get keys =>{
    "en":{
      // Side bar
      "home":"Home",
      "dashboard":"Dashboard",
      "notification":"Notifications",
      "settings":"Settings",
      // Button
      "cancel":"Cancel",
      "confirm":"Confirm",
      "save":"Save",
      // Title Header
      "selectLanguage":"Select language",
      "french":"French",
      "arabic":"Arabic",
      "english":"English",
      "editProfile":"Edit Profile",
      "editInformations":"Edit Information",
      "changeLanguage":"Change language",
      "logout":"Logout",
      "select":"Select",
      "selectTheLanguages":"Select the languages",
    },
    "fr":{
      // Side bar
      "home":"Accueil",
      "dashboard":"Tableau de bord",
      "settings":"Parameters",
      "notification":"Notifications",

      // Button
      "cancel":"Annuler",
      "confirm":"Confirmer",
      "save":"Enregistrer",

      // Title Header
      "selectLanguage": "Sélectionner la langue",
      "french": "Français",
      "arabic": "Arabe",
      "english": "Anglais",
      "editProfile": "Modifier le profil",
      "editInformations": "Modifier les informations",
      "changeLanguage": "Changer de langue",
      "Logout": "Déconnexion",
      "select":"Sélectionner",
      "selectTheLanguages":"Sélectionner les langues",

    },
    "ar": {
      // Side bar
      "home": "الرئيسية",
      "dashboard":"لوحة القيادة",
      "settings":"إعدادات",
      "notification":"الإشعارات",

      // Button
      "cancel":"إلغاء",
      "confirm":"تأكيد",
      "save":"حفظ",
      // Title Header
      "selectLanguage": "اختر اللغة",
      "french": "الفرنسية",
      "arabic": "العربية",
      "english": "الإنجليزية",
      "editProfile": "تعديل الملف الشخصي",
      "editInformations": "تعديل المعلومات",
      "changeLanguage": "تغيير اللغة",
      "logout": "تسجيل الخروج",
      "select":"اختر",
      "selectTheLanguages":"اختر اللغات",

    }
};}
