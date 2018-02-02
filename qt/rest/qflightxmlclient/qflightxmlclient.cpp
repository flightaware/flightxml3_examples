#include "qflightxmlclient.h"
#include <QJsonObject>
#include <QJsonDocument>
#include <QtNetwork>

QFlightXMLClient::QFlightXMLClient(QObject *parent) : QObject(parent)
{
    connect(&networkAccessManager, SIGNAL(finished(QNetworkReply*)), this, \
            SLOT(serviceRequestFinished(QNetworkReply*)));

    connect(&networkAccessManager, SIGNAL(authenticationRequired(QNetworkReply*,QAuthenticator*)),
            SLOT(provideAuthenication(QNetworkReply*,QAuthenticator*)));
}

void QFlightXMLClient::airlineInfoRequest(const QString& airlineCode)
{
    airline = airlineCode;
    QUrl url = fxmlUrl.append("/AirlineInfo");
    QUrlQuery query;
    query.addQueryItem("airline_code", airlineCode);

    url.setQuery(query.query());

    QNetworkRequest request(url);

    networkAccessManager.get(request);
}

void QFlightXMLClient::serviceRequestFinished(QNetworkReply *reply)
{
    if (reply->error() > 0)
    {
        qWarning() << reply->errorString();
        return;
    }

    QJsonDocument document = QJsonDocument::fromJson(reply->readAll());
    QJsonObject rootObj = document.object();

    if(!rootObj.empty())
    {
        parseAirlineInfo(rootObj);
    }
    else
    {
        qWarning() << "json is empty";
    }
}

void QFlightXMLClient::provideAuthenication(QNetworkReply *, QAuthenticator *ator)
{
    ator->setUser(Username);
    ator->setPassword(ApiKey);
}

void QFlightXMLClient::parseAirlineInfo(QJsonObject &rootObj)
{
    QJsonValue value = rootObj.value(QString("AirlineInfoResult"));
    QString airlineInfo("Airline name: %1; shortname: %2; callsign: %3; location: %4; "
                        "country: %5; phone: %6; url: %7; wiki_url: %8; "
                        "airbourne: %9");
    QJsonObject item = value.toObject();

    QJsonValue name = item.value(QString("name"));
    QJsonValue shortname = item.value(QString("shortname"));
    QJsonValue callsign = item.value(QString("callsign"));
    QJsonValue location = item.value(QString("location"));
    QJsonValue country = item.value(QString("country"));
    QJsonValue phone = item.value(QString("phone"));
    QJsonValue url = item.value(QString("url"));
    QJsonValue wiki_url = item.value(QString("wiki_url"));
    QJsonValue airbourne = item.value(QString("airbourne"));

    qDebug() << airlineInfo.arg(name.toString(), shortname.toString(), callsign.toString(),
                              location.toString(), country.toString(),
                              phone.toString(), url.toString(), wiki_url.toString(),
                                airbourne.toString());
}

