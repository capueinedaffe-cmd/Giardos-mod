#!/bin/bash
# Script MANUAL para instalar Android SDK.
# Ejecutar solo si necesitas compilar para Android localmente.
# Para pruebas rapidas usa: ./gradlew jar (no necesita Android SDK)

set -e

echo "Instalando Android SDK..."

sudo apt-get update
sudo apt-get install -y wget unzip

# Crear directorio
sudo mkdir -p /usr/local/android-sdk
sudo chown -R $(whoami):$(whoami) /usr/local/android-sdk

# Descargar command line tools
cd /tmp
wget -q https://dl.google.com/android/repository/commandlinetools-linux-11076708_latest.zip
unzip -q commandlinetools-linux-11076708_latest.zip
mkdir -p /usr/local/android-sdk/cmdline-tools
mv cmdline-tools /usr/local/android-sdk/cmdline-tools/latest

# Instalar build-tools
export PATH="/usr/local/android-sdk/cmdline-tools/latest/bin:$PATH"
yes | sdkmanager --licenses || true
sdkmanager "build-tools;34.0.0" "platforms;android-34"

echo "Android SDK instalado en: $ANDROID_HOME"
echo "Build tools disponibles:"
ls -la $ANDROID_HOME/build-tools/34.0.0/ 2>/dev/null || echo "No encontrado"
