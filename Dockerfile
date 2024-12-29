FROM ubuntu:latest
LABEL authors="julia"

ENTRYPOINT ["top", "-b"]