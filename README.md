### Вилочный калькулятор выполненный в качестве GraalVM native-image, с применением Gluon Substrate для использования JavaFX.

Приложение может быть скомпилировано для следующих ОС:

- Linux
- Mac OS X
- Windows
- IOS
- Android

### Подготовка для Windows

- Скачайте последнюю версию [GraalVM](https://github.com/graalvm/graalvm-ce-builds/releases/tag/vm-20.2.0), выбрав `graalvm-ce-java11-windows-amd64-20.2.0.zip` из списка ресурсов и распакуйте его в удобное для вас место.

- Установите Visual Studio 2019 со следующими компонентами:

- Языковой пакет: Английский

- Поддержка C++/CLI для средств сборки версии 142 (версии 14.25+)

- MSVC версии 142 - средства сборки C++ для Visual Studio 2019 для x64/x86 (версии 14.25+)

- Пакет SDK для универсальной CRT для Windows

- Windows 10 SDK (10.0.19041.0+)

- Выполните указанные ниже команды используя x64 Native Tools Command Prompt for VS 2019. Доступ к этой командной строке можно получить из меню «Пуск».

- Настройте среду выполнения. Установите `GRAALVM_HOME` переменную среды в каталог установки GraalVM.

Например: `set GRAALVM_HOME=C:\tools\graalvm-ce-java11-20.2.0`

- Установите `JAVA_HOME` как каталог, содержащий GraalVM.

Например: `set JAVA_HOME=%GRAALVM_HOME%`

### Подготовка для Linux и Android.

- Скачайте последнюю версию [GraalVM](https://github.com/graalvm/graalvm-ce-builds/releases/tag/vm-20.2.0), выбрав `graalvm-ce-java11-linux-amd64-20.2.0.tar.gz` из списка ресурсов и распакуйте его в удобное для вас место (например, в /opt).

- Настройте среду выполнения. Установите `GRAALVM_HOME` переменную среды в каталог установки GraalVM.

Например: `export GRAALVM_HOME=/opt/graalvm-ce-java11-20.2.0`

- Установите `JAVA_HOME` как каталог, содержащий GraalVM.

Например: `export JAVA_HOME=$GRAALVM_HOME`

### Подготовка для Mac OS X и iOS.

- Скачайте последнюю версию [GraalVM](https://github.com/graalvm/graalvm-ce-builds/releases/tag/vm-20.2.0), выбрав `graalvm-ce-java11-darwin-amd64-20.2.0.tar.gz` из списка ресурсов и распакуйте его в удобное для вас место (например, в /opt).

- Настройте среду выполнения. Установите `GRAALVM_HOME` переменную среды в каталог установки GraalVM.

Например: `export GRAALVM_HOME=/opt/graalvm-ce-java11-20.2.0/Contents/Home`

- Установите `JAVA_HOME` как каталог, содержащий GraalVM.

Например: `export JAVA_HOME=$GRAALVM_HOME`

### Сборка с помощью Gradle

Следующие цели применимы к Windows, Linux и Mac OS X.

Чтобы собрать native image (`NativeCompile` + `NativeLink`):

`gradlew nativeBuild`

Чтобы запустить native image:

`gradlew nativeRun`

Вы также может запустить исполняемый файл, который находится (Windows) в `build/client/x86_64-windows/`.
