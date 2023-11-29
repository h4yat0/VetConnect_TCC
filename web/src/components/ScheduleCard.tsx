interface ScheduleCardProps {
  id: number;
  status: number;
  animal: string;
  unit: string;
  service: string;
  scheduleDate: string;
  scheduleTime: string;
  handleSchedulingOpen: () => void;
}

export default function ScheduleCard(props: ScheduleCardProps) {
  const {
    status,
    animal,
    unit,
    service,
    scheduleDate,
    scheduleTime,
    handleSchedulingOpen,
  } = props;

  return (
    <div
      className={`p-4 flex space-x-4 rounded-lg shadow-md hover:scale-105 transition transform duration-500 cursor-pointer min-w-[275px]
      ${status == 3 ? "bg-red-400" : status == 2 ? "bg-vetConnectSecundaryGreen" : "bg-white"}`}
      onClick={() => handleSchedulingOpen()}
    >
      <div>
        <h1 className="text-start text-xl font-bold text-gray-700">{animal}</h1>
        <div className="flex flex-col">
          <span className="pt-[2px]">
            <b>Data e hora: </b>
            {scheduleDate} - {scheduleTime}
          </span>
          <span>
            <b>Unidade:</b> {unit}
          </span>

          <span>
            <b>Serviço</b> {service}
          </span>
        </div>
      </div>
    </div>
  );
}
