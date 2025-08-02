// Margin
//margin all
import 'package:flutter/cupertino.dart';

EdgeInsets marginAll(double value) => EdgeInsets.all(value);
EdgeInsets all5() => const EdgeInsets.all(5);
EdgeInsets all10() => const EdgeInsets.all(10);
EdgeInsets all15() => const EdgeInsets.all(15);
EdgeInsets all20() => const EdgeInsets.all(20);
EdgeInsets all25() => const EdgeInsets.all(25);
EdgeInsets all30() => const EdgeInsets.all(30);
EdgeInsets all35() => const EdgeInsets.all(35);
EdgeInsets all40() => const EdgeInsets.all(40);
EdgeInsets all45() => const EdgeInsets.all(45);
EdgeInsets all50() => const EdgeInsets.all(50);

// margin symmetric
EdgeInsets marginSymmetric({double vertical = 0, double horizontal = 0}) => EdgeInsets.symmetric(vertical: vertical, horizontal: horizontal);

 // margin symmetric horizontal
EdgeInsets symmetricH5() => const EdgeInsets.symmetric(vertical: 0, horizontal: 5);
EdgeInsets symmetricH10() => const EdgeInsets.symmetric(vertical: 0, horizontal: 10);
EdgeInsets symmetricH15() => const EdgeInsets.symmetric(vertical: 0, horizontal: 15);
EdgeInsets symmetricH20() => const EdgeInsets.symmetric(vertical: 0, horizontal: 20);
EdgeInsets symmetricH25() => const EdgeInsets.symmetric(vertical: 0, horizontal: 25);
EdgeInsets symmetricH30() => const EdgeInsets.symmetric(vertical: 0, horizontal: 30);
EdgeInsets symmetricH35() => const EdgeInsets.symmetric(vertical: 0, horizontal: 35);
EdgeInsets symmetricH40() => const EdgeInsets.symmetric(vertical: 0, horizontal: 40);
EdgeInsets symmetricH45() => const EdgeInsets.symmetric(vertical: 0, horizontal: 45);
EdgeInsets symmetricH50() => const EdgeInsets.symmetric(vertical: 0, horizontal: 50);
EdgeInsets symmetricV5() =>  const EdgeInsets.symmetric(vertical: 0, horizontal: 0);
// margin symmetric Vertical
EdgeInsets symmetricV10() => const EdgeInsets.symmetric(vertical: 0, horizontal: 0);
EdgeInsets symmetricV15() => const EdgeInsets.symmetric(vertical: 0, horizontal: 0);
EdgeInsets symmetricV20() => const EdgeInsets.symmetric(vertical: 0, horizontal: 0);
EdgeInsets symmetricV25() => const EdgeInsets.symmetric(vertical: 0, horizontal: 0);
EdgeInsets symmetricV30() => const EdgeInsets.symmetric(vertical: 0, horizontal: 0);
EdgeInsets symmetricV35() => const EdgeInsets.symmetric(vertical: 0, horizontal: 0);
EdgeInsets symmetricV40() => const EdgeInsets.symmetric(vertical: 0, horizontal: 0);
EdgeInsets symmetricV45() => const EdgeInsets.symmetric(vertical: 0, horizontal: 0);
EdgeInsets symmetricV50() => const EdgeInsets.symmetric(vertical: 0, horizontal: 0);






EdgeInsets marginOnly({double left = 0, double right = 0, double top = 0, double bottom = 0}) => EdgeInsets.only(left: left, right: right, top: top, bottom: bottom);
// margin only  left
EdgeInsets onlyL5() => const EdgeInsets.only(left: 5, right: 0, top: 0, bottom: 0);
EdgeInsets onlyL10() => const EdgeInsets.only(left: 10, right: 0, top: 0, bottom: 0);
EdgeInsets onlyL15() => const EdgeInsets.only(left: 15, right: 0, top: 0, bottom: 0);
EdgeInsets onlyL20() => const EdgeInsets.only(left: 20, right: 0, top: 0, bottom: 0);
EdgeInsets onlyL25() => const EdgeInsets.only(left: 25, right: 0, top: 0, bottom: 0);
EdgeInsets onlyL30() => const EdgeInsets.only(left: 30, right: 0, top: 0, bottom: 0);
EdgeInsets onlyL35() => const EdgeInsets.only(left: 35, right: 0, top: 0, bottom: 0);
EdgeInsets onlyL40() => const EdgeInsets.only(left: 40, right: 0, top: 0, bottom: 0);
EdgeInsets onlyL45() => const EdgeInsets.only(left: 45, right: 0, top: 0, bottom: 0);
EdgeInsets onlyL50() => const EdgeInsets.only(left: 50, right: 0, top: 0, bottom: 0);
// margin only  right
EdgeInsets onlyR5() => const EdgeInsets.only(left: 0, right: 5, top: 0, bottom: 0);
EdgeInsets onlyR10() => const EdgeInsets.only(left: 0, right: 10, top: 0, bottom: 0);
EdgeInsets onlyR15() => const EdgeInsets.only(left: 0, right: 15, top: 0, bottom: 0);
EdgeInsets onlyR20() => const EdgeInsets.only(left: 0, right: 20, top: 0, bottom: 0);
EdgeInsets onlyR25() => const EdgeInsets.only(left: 0, right: 25, top: 0, bottom: 0);
EdgeInsets onlyR30() => const EdgeInsets.only(left: 0, right: 30, top: 0, bottom: 0);
EdgeInsets onlyR35() => const EdgeInsets.only(left: 0, right: 35, top: 0, bottom: 0);
EdgeInsets onlyR40() => const EdgeInsets.only(left: 0, right: 40, top: 0, bottom: 0);
EdgeInsets onlyR45() => const EdgeInsets.only(left: 0, right: 45, top: 0, bottom: 0);
EdgeInsets onlyR50() => const EdgeInsets.only(left: 0, right: 50, top: 0, bottom: 0);
// only top
EdgeInsets onlyT5() => const EdgeInsets.only(left: 0, right: 0, top: 5, bottom: 0);
EdgeInsets onlyT10() => const EdgeInsets.only(left: 0, right: 0, top: 10, bottom: 0);
EdgeInsets onlyT15() => const EdgeInsets.only(left: 0, right: 0, top: 15, bottom: 0);
EdgeInsets onlyT20() => const EdgeInsets.only(left: 0, right: 0, top: 20, bottom: 0);
EdgeInsets onlyT25() => const EdgeInsets.only(left: 0, right: 0, top: 25, bottom: 0);
EdgeInsets onlyT30() => const EdgeInsets.only(left: 0, right: 0, top: 30, bottom: 0);
EdgeInsets onlyT35() => const EdgeInsets.only(left: 0, right: 0, top: 35, bottom: 0);
EdgeInsets onlyT40() => const EdgeInsets.only(left: 0, right: 0, top: 40, bottom: 0);
EdgeInsets onlyT45() => const EdgeInsets.only(left: 0, right: 0, top: 45, bottom: 0);
EdgeInsets onlyT50() => const EdgeInsets.only(left: 0, right: 0, top: 50, bottom: 0);
// only bottom
EdgeInsets onlyB5() => const EdgeInsets.only(left: 0, right: 0, top: 0, bottom: 5);
EdgeInsets onlyB10() => const EdgeInsets.only(left: 0, right: 0, top: 0, bottom: 10);
EdgeInsets onlyB15() => const EdgeInsets.only(left: 0, right: 0, top: 0, bottom: 15);
EdgeInsets onlyB20() => const EdgeInsets.only(left: 0, right: 0, top: 0, bottom: 20);
EdgeInsets onlyB25() => const EdgeInsets.only(left: 0, right: 0, top: 0, bottom: 25);
EdgeInsets onlyB30() => const EdgeInsets.only(left: 0, right: 0, top: 0, bottom: 30);
EdgeInsets onlyB35() => const EdgeInsets.only(left: 0, right: 0, top: 0, bottom: 35);
EdgeInsets onlyB40() => const EdgeInsets.only(left: 0, right: 0, top: 0, bottom: 40);
EdgeInsets onlyB45() => const EdgeInsets.only(left: 0, right: 0, top: 0, bottom: 45);
EdgeInsets onlyB50() => const EdgeInsets.only(left: 0, right: 0, top: 0, bottom: 50);



