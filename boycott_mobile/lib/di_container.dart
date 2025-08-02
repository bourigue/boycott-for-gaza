import 'package:connectivity_plus/connectivity_plus.dart';
import 'package:dio/dio.dart';
import 'package:get_it/get_it.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'DesignSystem/data/remote/dio/dio_client.dart';
import 'DesignSystem/data/remote/dio/logging_interceptor.dart';
import 'DesignSystem/resources/app_constants.dart';

final sl = GetIt.instance;
Future<void> init() async {
  // Core
  final sharedPreferences = await SharedPreferences.getInstance();
  sl.registerLazySingleton(() => sharedPreferences);
  sl.registerLazySingleton(() => Dio());
  sl.registerLazySingleton(() => LoggingInterceptor());
  sl.registerLazySingleton(() => Connectivity());
  sl.registerLazySingleton(() => DioClient(AppConstants.baseUrl, sl(),
      loggingInterceptor: sl(), sharedPreferences: sl()));
}
