# Define parâmetros como variáveis
[string]$GATLING_VERSION = "3.9.5"
[string]$DEPS_PATH = "deps"
[string]$GATLING_DIR = "gatling"
[string]$DOWNLOAD_URL = "https://repo1.maven.org/maven2/io/gatling/highcharts/gatling-charts-highcharts-bundle/$GATLING_VERSION/gatling-charts-highcharts-bundle-$GATLING_VERSION-bundle.zip"

Write-Host "=== Iniciando instalação do Gatling ===" -ForegroundColor Green

try {
    # Cria o diretório deps se não existir
    if (-Not (Test-Path -Path $DEPS_PATH)) {
        Write-Host "Criando diretório $DEPS_PATH..." -ForegroundColor Cyan
        New-Item -ItemType Directory -Path $DEPS_PATH -ErrorAction Stop
    }

    Write-Host "Iniciando download do Gatling versão $GATLING_VERSION..." -ForegroundColor Cyan

    # Armazena o local inicial
    $initialLocation = Get-Location

    # Baixa o arquivo zip do Gatling com verificação
    try {
        $ProgressPreference = 'SilentlyContinue'  # Acelera o download
        Write-Host "Baixando de: $DOWNLOAD_URL" -ForegroundColor Yellow
        Invoke-WebRequest -Uri $DOWNLOAD_URL -OutFile "$DEPS_PATH/gatling.zip" -ErrorAction Stop
    }
    catch {
        throw "Falha ao baixar Gatling: $_"
    }

    # Navega para o diretório deps
    Set-Location -Path $DEPS_PATH -ErrorAction Stop

    # Remove diretório gatling existente se houver
    if (Test-Path -Path $GATLING_DIR) {
        Write-Host "Removendo instalação anterior do Gatling..." -ForegroundColor Cyan
        Remove-Item -Recurse -Force $GATLING_DIR -ErrorAction Stop
    }

    # Extrai o conteúdo do zip
    Write-Host "Extraindo arquivos..." -ForegroundColor Cyan
    Expand-Archive -Path "gatling.zip" -DestinationPath "." -ErrorAction Stop

    # Renomeia o diretório extraído
    Write-Host "Configurando diretório..." -ForegroundColor Cyan
    Rename-Item -Path "gatling-charts-highcharts-bundle-$GATLING_VERSION" -NewName $GATLING_DIR -ErrorAction Stop

    # Remove o arquivo zip
    Remove-Item -Path "gatling.zip" -ErrorAction Stop

    Write-Host "Instalação do Gatling concluída com sucesso!" -ForegroundColor Green
}
catch {
    Write-Host "ERRO durante a instalação:" -ForegroundColor Red
    Write-Host $_.Exception.Message -ForegroundColor Red
    # Garante que voltamos ao diretório original mesmo em caso de erro
    if ($initialLocation) {
        Set-Location -Path $initialLocation
    }
}
finally {
    # Sempre retorna ao diretório original
    if ($initialLocation) {
        Set-Location -Path $initialLocation
    }
    
    Write-Host "`nPressione qualquer tecla para fechar..." -ForegroundColor Yellow
    $null = $Host.UI.RawUI.ReadKey('NoEcho,IncludeKeyDown')
}


# Set-ExecutionPolicy Bypass -Scope Process