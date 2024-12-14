FROM postgres:15

COPY init /docker-entrypoint-initdb.d/
COPY cmn /app/cmn
COPY tool /app/tool

ENV PATH="/app/cmn:$PATH"

ENV DB_USER=postgres
ENV DB_PASSWORD=authdb
ENV DB_NAME=auth_db
ENV DB_PORT=5432
