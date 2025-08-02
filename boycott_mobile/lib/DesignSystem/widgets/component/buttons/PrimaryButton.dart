import 'package:flutter/material.dart';

import '../../../template/app_colors.dart';

enum ButtonType { Basic,BasicIcon, Raised,RaisedIcon, Stroked,ButtonAwait, Flat, Icon, FAB, MiniFAB }

class dsButton extends StatelessWidget {
  final ButtonType type;
  final String label;
  final IconData? icon;
  final VoidCallback? onPressed;
  final bool enabled;
  final bool? isDelete ;
  dsButton({
    required this.type,
    required this.label,
    this.icon,
    this.onPressed,
    this.enabled = true,
    this.isDelete=false,
  });

  @override
  Widget build(BuildContext context) {
    switch (type) {
      case ButtonType.Basic:
        return ElevatedButton(
          onPressed: enabled ? onPressed : null,
          style: ElevatedButton.styleFrom(
          foregroundColor: AppColors.WHITE_COLOR, backgroundColor:AppColors.PRIMARY_COLOR),
          child: Text(label),

        );
      case ButtonType.BasicIcon:
        return ElevatedButton.icon(
          onPressed: enabled ? onPressed : null,
          style: ElevatedButton.styleFrom(
          foregroundColor: AppColors.WHITE_COLOR, backgroundColor: AppColors.PRIMARY_COLOR),
          label: Text(label),
          icon: Icon(icon),

        );
      case ButtonType.Raised:
        return ElevatedButton(
          onPressed: enabled ? onPressed : null,
          style: ElevatedButton.styleFrom(elevation: 4),
          child: Text(label),


        );
        case ButtonType.RaisedIcon:
        return ElevatedButton.icon(
          onPressed: enabled ? onPressed : null,
          label: Text(label),
          icon: Icon(icon),

        );
      case ButtonType.Stroked:
        return OutlinedButton(
          onPressed: enabled ? onPressed : null,
          style: ElevatedButton.styleFrom(
            side:  BorderSide(color:(isDelete==true)? AppColors.RED_COLOR:AppColors.PRIMARY_COLOR,),
            backgroundColor: (isDelete==true) ? AppColors.RED_COLOR.withOpacity(0.3):Colors.transparent,),
            child: Text(label,style: TextStyle(color: (isDelete==true) ? AppColors.RED_COLOR:AppColors.PRIMARY_COLOR),
        ));
        case ButtonType.ButtonAwait:
        return ElevatedButton(
          onPressed:  null,
          style: ElevatedButton.styleFrom(
              foregroundColor: AppColors.WHITE_COLOR, backgroundColor: AppColors.PRIMARY_COLOR

          ),
          child:const SizedBox(
            width: 30.0,
            height: 30.0,
            child:  CircularProgressIndicator(
              valueColor: AlwaysStoppedAnimation<Color>(AppColors.PRIMARY_COLOR),
              strokeWidth: 4.0,
             strokeCap: StrokeCap.round,
            ),
          ),
        );

      case ButtonType.Flat:
        return TextButton(
          onPressed: enabled ? onPressed : null,
          child: Text(label),
        );
      case ButtonType.Icon:
        return IconButton(
          icon: Icon(icon,color:AppColors.PRIMARY_COLOR,),
          onPressed: enabled ? onPressed : null,

        );
      case ButtonType.FAB:
        return FloatingActionButton(
          onPressed: enabled ? onPressed : null,
          backgroundColor:AppColors.PRIMARY_COLOR ,
          child: Icon(icon,color:AppColors.WHITE_COLOR),

        );
      case ButtonType.MiniFAB:
        return FloatingActionButton(
          onPressed: enabled ? onPressed : null,
          mini: true,
          backgroundColor:AppColors.PRIMARY_COLOR ,
          child: Icon(icon,color: AppColors.WHITE_COLOR),

        );
      default:
        return ElevatedButton(
          onPressed: enabled ? onPressed : null,
          child: Text(label),
        );
    }
  }
}
