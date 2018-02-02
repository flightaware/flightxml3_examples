#ifndef QFLIGHTXMLCLIENT_H
#define QFLIGHTXMLCLIENT_H

#include <QObject>
#include <QNetworkAccessManager>

class QNetworkReply;
class QAuthenticator;

class QFlightXMLClient : public QObject
{
    Q_OBJECT
public:
    explicit QFlightXMLClient(QObject *parent = nullptr);
    void airlineInfoRequest(const QString& airlineCode);

private slots:
    void serviceRequestFinished(QNetworkReply*);
    void provideAuthenication(QNetworkReply *reply, QAuthenticator *ator);

private:
    QString fxmlUrl = "http://flightxml.flightaware.com/json/FlightXML3";
    const QString Username = "slydog1";
    const QString ApiKey = "46a113b8bec4e98da029306e4a605c1fb9d005b1";

    QNetworkAccessManager networkAccessManager;
    QString airline;

    void parseAirlineInfo(QJsonObject &rootObj);
};

#endif // QFLIGHTXMLCLIENT_H
