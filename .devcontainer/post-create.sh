#!/bin/bash
set -e

echo "=== Configurando entorno Giardos Mod ==="

# Verificar Java 17
if [ ! -d "/usr/lib/jvm/java-17-openjdk-amd64" ]; then
    echo "Instalando OpenJDK 17..."
    sudo apt-get update
    sudo apt-get install -y openjdk-17-jdk
fi

echo "Java version:"
java -version

# Verificar Gradle
echo "Gradle version:"
./gradlew --version || echo "Gradle wrapper no encontrado, se usara el de la imagen"

# Configurar Android SDK si no existe
if [ ! -d "$ANDROID_HOME" ]; then
    echo "Configurando Android SDK..."
    sudo mkdir -p /usr/local/android-sdk
    sudo chown -R vscode:vscode /usr/local/android-sdk

    # Descargar command line tools
    cd /tmp
    wget -q https://dl.google.com/android/repository/commandlinetools-linux-11076708_latest.zip
    unzip -q commandlinetools-linux-11076708_latest.zip
    mkdir -p /usr/local/android-sdk/cmdline-tools
    mv cmdline-tools /usr/local/android-sdk/cmdline-tools/latest

    # Instalar build-tools
    export PATH="/usr/local/android-sdk/cmdline-tools/latest/bin:$PATH"
    yes | sdkmanager --licenses
    sdkmanager "build-tools;34.0.0" "platforms;android-34"
fi

# Verificar build-tools
if [ -d "$ANDROID_HOME/build-tools/34.0.0" ]; then
    echo "Android Build Tools 34.0.0 OK"
else
    echo "ADVERTENCIA: Build Tools 34.0.0 no encontrado"
fi

# Hacer gradlew ejecutable
if [ -f "gradlew" ]; then
    chmod +x gradlew
    echo "gradlew listo"
fi

echo "=== Entorno listo ==="
echo "JAVA_HOME=$JAVA_HOME"
echo "ANDROID_HOME=$ANDROID_HOME"
echo ""
echo "Para compilar el mod:"
echo "  ./gradlew jar          (solo Desktop)"
echo "  ./gradlew deploy       (Android + Desktop - requiere Android SDK)"
