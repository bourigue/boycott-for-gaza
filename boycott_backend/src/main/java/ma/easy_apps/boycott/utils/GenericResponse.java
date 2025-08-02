package ma.easy_apps.boycott.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse<T> {
    private boolean success;
    private String message;
    private T data;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    
    public static <T> GenericResponse<T> success(T data, String message) {
        return new GenericResponse<>(true, message, data, LocalDateTime.now());
    }
    
    public static <T> GenericResponse<T> success(T data) {
        return success(data, "Operation completed successfully");
    }
    
    public static <T> GenericResponse<T> error(String message) {
        return new GenericResponse<>(false, message, null, LocalDateTime.now());
    }
    
    public static <T> GenericResponse<T> error(String message, T data) {
        return new GenericResponse<>(false, message, data, LocalDateTime.now());
    }
}
