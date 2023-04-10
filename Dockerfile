FROM alpine:3.14

# Install required packages
RUN apk add --no-cache openjdk11
RUN apk add --no-cache curl

# Create directory
RUN mkdir -p /usr/share/services && \
	cd /usr/share/services

RUN wget -O buggyservice.jar https://github.com/kdrzazga/buggy-webservice/releases/download/v.0.4.5/buggywebservice-0.4.5-RELEASE.jar

# Copy JARs
RUN cp buggyservice.jar /usr/share/services/
RUN addgroup -g 10000 testers && \
    adduser -u 10000 -G testers -s /bin/sh -D userX
RUN chown -R userX:testers /usr/share/services

USER userX

# Start service
EXPOSE 8081 50000
CMD ["java", "-jar", "/usr/share/services/buggyservice.jar"]
