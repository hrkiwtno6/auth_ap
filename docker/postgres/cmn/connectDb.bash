#!/bin/bash

# 環境変数チェック
if [ -z "$DB_USER" ] || [ -z "$DB_PASSWORD" ] || [ -z "$DB_NAME" ] || [ -z "$DB_PORT" ]; then
  echo "環境変数エラー"
  exit 1
fi

# 接続先文字列
CONNECTION_STRING="postgresql://$DB_USER:$DB_PASSWORD@localhost:$DB_PORT/$DB_NAME"

# PSQLに接続
psql "$CONNECTION_STRING"