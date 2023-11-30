**Note: Vui lòng mở tệp này trong VScode để dễ đọc!**


# Entity-relationship diagram
- File spring-ecommerce.mdj
- Hãy sử dụng StarUML để mở nó (Link download: https://staruml.io/)

# Giải thích ngắn gọn về cấu trúc mã.
- Các thư mục, file hiện có được tạo bởi Spring Launchizer
- Bên trong src/main/java:
  + Package entity contains classes và fields được biểu diễn dưới dạng bảng và cột, các mối quan hệ
  + Package repository contains interfaces those extend JpaRepository để xử lý lớp dữ liệu
  + Package service contains service classes xử lý logic nghiệp vụ
  + Package controller contains RestController classes để ánh xạ phương thức url và http với chức năng cụ thể
  + Package security contains configuration  xử lý các vấn đề bảo mật của dự án
  + Package config chứa file cấu hình cho AWS, OpenAPI (Swagger) và Cors
  + Package enums chứa enums của dự án
  + Package utilities chứa các chức năng chung có thể tái sử dụng xuyên suốt toàn bộ dự án
- Bên trong src/main/resource:
  + file schema.sql định nghĩa script sql để thả và tạo lại bảng
  + file data.sql định nghĩa script sql để khởi tạo dữ liệu
  + file application.properties xác định cấu hình chung cho dự án như kết nối cơ sở dữ liệu, cổng khởi động, thông tin đăng nhập aws,...

# Tất cả các bước cần thiết để chạy ứng dụng trên local computer
- Các phụ thuộc bắt buộc: 
  + node v14.21.2   (run "node -v" to verify)
  + npm 6.14.17     (run "npm -v" to verify)
  + jdk 11          (run "java -version" to verify)
  + IDE to run springboot (BE): Eclipse
  + IDE to run react (FE): VSCode

# Các lệnh CURL đầy đủ (bao gồm các điểm cuối yêu cầu đầy đủ, Tiêu đề HTTP và tải trọng yêu cầu nếu có)
-  Tất cả đều được hiển thị trong swagger ui

- Steps:
  `Database`
    + Tải xuống và chạy máy chủ mysql cục bộ tại cổng 3306
  `Backend`
    + Import folder be to eclipse project
    + Nhấp chuột phải vào project -> Maven ->  Update Project  -> Tick Offline  -> OK
    + Run As Spring Boot App
    + Vào  [http://localhost:8080/swagger-ui/index.html]
    + Nên xem lược đồ spring_ecommerce, bảng và dữ liệu init
  `Frontend`
    + Mở thư mục fe trong VScode
    + Mở integrated terminal(terminal nên để ở thư mục fe)
    + Gõ "npm install"
    + Gõ "npm start"
    + Vào [http://localhost:3002]

# Yêu cầu bổ sung
- Đã tích hợp:
   + Spring Security (áp dụng JWT)
   + API mở (Swagger UI)
   + AWS S3 (Lưu trữ hình ảnh)