#include <QCoreApplication>
#include <qflightxmlclient.h>

int main(int argc, char *argv[])
{
    QCoreApplication a(argc, argv);

    QFlightXMLClient client;
    const QString airlineCode("SBI");
    client.airlineInfoRequest(airlineCode);

    return a.exec();
}
